package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.ERequestMethod;
import com.javable.daleks.models.Level;
import com.javable.daleks.models.ApiResponse;
import javafx.util.Pair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ServiceManager {
    public Level[] getLevels(String endpoint) {
        Level[] levels;

        try {
            HttpURLConnection http = getConnection(endpoint, ERequestMethod.GET);
            JSONArray jsonArray = new JSONArray(getResponse(http));
            levels = new Level[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
                levels[i] = Level.ParseLevelJson(jsonArray.getJSONObject(i), Objects.equals(endpoint, Settings.GetCampaignLevels));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

       return levels;
    }

    public ApiResponse uploadLevel(Level level) {
        ApiResponse jsonResult;

        try {
            HttpURLConnection http = getConnection(Settings.PostLevel, ERequestMethod.POST);

            List<Pair<String, String>> body = List.of(
                    new Pair<>("lvName", level.levelName),
                    new Pair<>("gridCount", Integer.toString(level.getGridSize())),
                    new Pair<>("daleksCount",Integer.toString(level.getDaleksCount()))
            );
            writePostFormData(http, body);

            jsonResult = new ApiResponse(new JSONObject(getResponse(http)));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return jsonResult;
    }

    public ApiResponse deleteLevel(String levelName) {
        ApiResponse jsonResult;

        try {
            HttpURLConnection http = getConnection(Settings.DeleteLevel, ERequestMethod.POST);

            List<Pair<String, String>> body = List.of(
                    new Pair<>("lvName", levelName)
            );
            writePostFormData(http, body);

            jsonResult = new ApiResponse(new JSONObject(getResponse(http)));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return jsonResult;
    }

    private void writePostFormData(HttpURLConnection http, List<Pair<String, String>> body) throws IOException {
        String boundary = UUID.randomUUID().toString();
        byte[] boundaryBytes = ("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8);
        byte[] finishBoundaryBytes = ("--" + boundary + "--").getBytes(StandardCharsets.UTF_8);
        http.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + boundary);
        http.setChunkedStreamingMode(0);

        OutputStream out = http.getOutputStream();

        for (Pair<String, String> formDataPart : body) {
            out.write(boundaryBytes);
            sendField(out, formDataPart.getKey(), formDataPart.getValue());
        }

        out.write(boundaryBytes);
        out.write(finishBoundaryBytes);
    }

    private HttpURLConnection getConnection(String endpoint, ERequestMethod method) throws IOException {
        URL url = new URL(Settings.ServiceUrl + endpoint);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod(method.toString());

        if (method == ERequestMethod.POST)
            http.setDoOutput(true);

        return http;
    }

    private void sendField(OutputStream out, String name, String field) throws IOException {
        String o = "Content-Disposition: form-data; name=\""
                + URLEncoder.encode(name, StandardCharsets.UTF_8) + "\"\r\n\r\n";
        out.write(o.getBytes(StandardCharsets.UTF_8));
        out.write(URLEncoder.encode(field, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        String line;
        StringBuilder json = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((line = reader.readLine()) != null)
            json.append(line);

        return json.toString();
    }
}
