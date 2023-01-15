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
import java.util.UUID;

public class ServiceManager {
    public Level[] GetAllUserLevels() {
        Level[] levels;

        try {
            HttpURLConnection http = GetConnection(Settings.GetUserLevels, ERequestMethod.GET);
            JSONArray jsonArray = new JSONArray(GetResponse(http));
            levels = new Level[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                levels[i] = new Level(
                        jsonObject.getInt("gridCount"),
                        jsonObject.getInt("daleksCount"),
                        jsonObject.getString("lvName"));
            }
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

       return levels;
    }

    public Level[] GetAllCampaignLevels() {
        // TODO implement

        String DEMO_JSON = """
            [
                {
                    "lvId": 1,
                    "lvName": "campagin lv 1",
                    "gridCount": 10,
                    "player": { "x": 7, "y": 7 },
                    "daleks": [
                        { "x": 1, "y": 1 },
                        { "x": 2, "y": 2 }
                    ]
                },
                {
                    "lvId": 2,
                    "lvName": "campagin lv 2",
                    "gridCount": 10,
                    "player": { "x": 7, "y": 7 },
                    "daleks": [
                        { "x": 1, "y": 1 },
                        { "x": 2, "y": 2 },
                        { "x": 3, "y": 3 }
                    ]
                },
                {
                    "lvId": 3,
                    "lvName": "campagin lv 3",
                    "gridCount": 10,
                    "player": { "x": 7, "y": 7 },
                    "daleks": [
                        { "x": 1, "y": 1 },
                        { "x": 2, "y": 2 },
                        { "x": 3, "y": 3 },
                        { "x": 4, "y": 4 }
                    ]
                }
            ]
        """;

        Level[] levels;

        try {
            // TODO HttpURLConnection http = GetConnection(Settings.GetCampaignLevels, ERequestMethod.GET);
            // TODO JSONArray jsonArray = new JSONArray(GetResponse(http));
            JSONArray jsonArray = new JSONArray(DEMO_JSON);
            levels = new Level[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
                levels[i] = new Level(jsonArray.getJSONObject(i));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return levels;
    }

    public ApiResponse UploadLevel(Level level) {
        ApiResponse jsonResult;

        try {
            HttpURLConnection http = GetConnection(Settings.PostLevel, ERequestMethod.POST);

            List<Pair<String, String>> body = List.of(
                    new Pair<>("lvName", level.LevelName),
                    new Pair<>("gridCount", Integer.toString(level.GridCount))
                    // TODO new Pair<>("daleksCount",Integer.toString(level.getDaleksCount()))
            );
            WritePostFormData(http, body);

            jsonResult = new ApiResponse(new JSONObject(GetResponse(http)));
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return jsonResult;
    }

    public ApiResponse DeleteLevel(String levelName) {
        ApiResponse jsonResult;

        try {
            HttpURLConnection http = GetConnection(Settings.DeleteLevel, ERequestMethod.POST);

            List<Pair<String, String>> body = List.of(
                    new Pair<>("lvName", levelName)
            );
            WritePostFormData(http, body);

            jsonResult = new ApiResponse(new JSONObject(GetResponse(http)));
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
