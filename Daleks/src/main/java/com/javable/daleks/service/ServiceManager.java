package com.javable.daleks.service;

import com.javable.daleks.Settings;
import com.javable.daleks.enums.ERequestMethod;
import com.javable.daleks.models.GameMapSettings;
import com.javable.daleks.models.JsonResult;
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
import java.util.UUID;

public class ServiceManager {
    public GameMapSettings[] GetAllLevels() {
        GameMapSettings[] levels;

        try {
            HttpURLConnection http = GetConnection(Settings.GetLevels, ERequestMethod.GET);
            JSONArray jsonArray = new JSONArray(GetResponse(http));
            levels = new GameMapSettings[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
                levels[i] = new GameMapSettings(jsonArray.getJSONObject(i));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

       return levels;
    }

    public JsonResult UploadLevel(GameMapSettings level) {
        JsonResult jsonResult;

        try {
            HttpURLConnection http = GetConnection(Settings.PostLevel, ERequestMethod.POST);

            List<Pair<String, String>> body = List.of(
                    new Pair<>("lvName", level.getLevelName()),
                    new Pair<>("gridCount", Integer.toString(level.getGridCount())),
                    new Pair<>("daleksCount",Integer.toString(level.getDaleksCount()))
            );
            WritePostFormData(http, body);

            jsonResult = new JsonResult(new JSONObject(GetResponse(http)));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return jsonResult;
    }

    public JsonResult DeleteLevel(String levelName) {
        JsonResult jsonResult;

        try {
            HttpURLConnection http = GetConnection(Settings.DeleteLevel, ERequestMethod.POST);

            List<Pair<String, String>> body = List.of(
                    new Pair<>("lvName", levelName)
            );
            WritePostFormData(http, body);

            jsonResult = new JsonResult(new JSONObject(GetResponse(http)));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return jsonResult;
    }

    private void WritePostFormData(HttpURLConnection http, List<Pair<String, String>> body) throws IOException {
        String boundary = UUID.randomUUID().toString();
        byte[] boundaryBytes = ("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8);
        byte[] finishBoundaryBytes = ("--" + boundary + "--").getBytes(StandardCharsets.UTF_8);
        http.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + boundary);
        http.setChunkedStreamingMode(0);

        OutputStream out = http.getOutputStream();

        for (Pair<String, String> formDataPart : body) {
            out.write(boundaryBytes);
            SendField(out, formDataPart.getKey(), formDataPart.getValue());
        }

        out.write(boundaryBytes);
        out.write(finishBoundaryBytes);
    }

    private HttpURLConnection GetConnection(String endpoint, ERequestMethod method) throws IOException {
        URL url = new URL(Settings.ServiceUrl + endpoint);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod(method.toString());

        if (method == ERequestMethod.POST)
            http.setDoOutput(true);

        return http;
    }

    private void SendField(OutputStream out, String name, String field) throws IOException {
        String o = "Content-Disposition: form-data; name=\""
                + URLEncoder.encode(name, StandardCharsets.UTF_8) + "\"\r\n\r\n";
        out.write(o.getBytes(StandardCharsets.UTF_8));
        out.write(URLEncoder.encode(field, StandardCharsets.UTF_8).getBytes(StandardCharsets.UTF_8));
        out.write("\r\n".getBytes(StandardCharsets.UTF_8));
    }

    private String GetResponse(HttpURLConnection connection) throws IOException {
        String line;
        StringBuilder json = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while ((line = reader.readLine()) != null)
            json.append(line);

        return json.toString();
    }
}
