package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import java.io.*;

public class Main {
    // Адрес ресурса
    public static final String REMOTE_SERVICE_URL = "https://api.nasa.gov/planetary/apod?api_key=ONmcDWgU8QsNPZxhrgEzeZek4eQBSVI9rGtOJoEM";
    // Каталог, куда будем сохранять файлы
    public static final String FOLDER_TO_UPLOAD = "./uploads";
    public static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setUserAgent("My User Agent")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        //создание объекта запроса с произвольными заголовками
        HttpGet request = new HttpGet(REMOTE_SERVICE_URL);
        request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

        //Отправка запроса
        CloseableHttpResponse response = httpClient.execute(request);

        // Чтение тела ответа и создание из него экземпляра nasaDataItem
        NasaDataItem nasaDataItem = mapper.readValue(
                response.getEntity().getContent(),
                new TypeReference<>() {
                }
        );

        //Выведем полученный объект в консоль
        System.out.println(nasaDataItem);

        // Получаем адрес изображения или видео в виде строки
        String urlString = nasaDataItem.getContentUrl();

        // Преобразуем в массив строк, чтобы была возможность взять имя файла
        String[] urlArray = urlString.split("/");

        // Имя файла - последний элемент
        String fileName = urlArray[urlArray.length-1];

        // Если нет каталога для сохраненных файлов - создадим его
        File uploadsDir = new File(FOLDER_TO_UPLOAD);
        if (!uploadsDir.exists()){
            uploadsDir.mkdirs();
        }

        if (urlString.length() > 0) {

            final CloseableHttpClient httpclient = HttpClients.createDefault();
            final HttpUriRequest httpGet = new HttpGet(urlString);

            try(CloseableHttpResponse response2 = httpclient.execute(httpGet)){
                final HttpEntity entity = response2.getEntity();
                byte[] buffer = EntityUtils.toByteArray(entity);

                // создаем выходной байтовый пото и передаем его в выходной буферизированный поток
                try (FileOutputStream out = new FileOutputStream(FOLDER_TO_UPLOAD+"/"+fileName);
                     BufferedOutputStream bos = new BufferedOutputStream(out)) {
                    // производим запись от 0 до последнего байта из массива
                    bos.write(buffer, 0, buffer.length);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        response.close();
        httpClient.close();
    }

}