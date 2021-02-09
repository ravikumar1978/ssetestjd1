package com.jd.ssetest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonUtil {
    public static <T> String convertToJson(T javaObject) {
        ObjectMapper mapper = new ObjectMapper();
        if (javaObject == null) {
            return "{}";
        }
        try {
           
            return mapper.writeValueAsString(javaObject);
        } catch (JsonGenerationException e) {
            
            return "{}";
        } catch (JsonMappingException e2) {
            
            return "{}";
        } catch (IOException e3) {
            
            return "{}";
        } 
    }

    public static <T> T convertToPojo(String jsonString, Class<T> className) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            
            if (jsonString == null){
                return null;
            }
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            return objectMapper.readValue(jsonString, className);
        } catch (JsonGenerationException e) {

            return null;
        } catch (JsonMappingException e2) {
            return null;
        } catch (IOException e3) {
            return null;
        }
    }

    public static <T> T convertToList(String jsonString, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonString == null) {
            return null;
        }
        try {
            
            return objectMapper.readValue(jsonString, (JavaType) objectMapper.getTypeFactory().constructCollectionType((Class<? extends Collection>) List.class, Class.forName(target.getName())));
        } catch (JsonGenerationException e) {
            return null;
        } catch (JsonMappingException e2) {
            return null;
        } catch (IOException e3) {
            return null;
        } catch (ClassNotFoundException e4) {
            
            return null;
        }
    }

    public static <T> T convertToSet(String jsonString, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonString == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, (JavaType) objectMapper.getTypeFactory().constructCollectionType((Class<? extends Collection>) Set.class, Class.forName(target.getName())));
        } catch (JsonGenerationException e) {
            
            return null;
        } catch (JsonMappingException e2) {
            
            return null;
        } catch (IOException e3) {
            
            return null;
        } catch (ClassNotFoundException e4) {
            
            return null;
        }
    }

    public static <T> T convertToLinkedSet(String jsonString, Class<?> target) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonString == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonString, (JavaType) objectMapper.getTypeFactory().constructCollectionType((Class<? extends Collection>) LinkedHashSet.class, Class.forName(target.getName())));
        } catch (JsonGenerationException e) {            
            return null;
        } catch (JsonMappingException e2) {            
            return null;
        } catch (IOException e3) {            
            return null;
        } catch (ClassNotFoundException e4) {            
            return null;
        }
    }

    public static <T> T convertToHashMap(String jsonString, Class<?> cls) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonString == null) {
            return null;
        }
        try {
            
            return objectMapper.readValue(jsonString, objectMapper.getTypeFactory().constructType((TypeReference<?>) new TypeReference<Map<String, String>>() {
            }));
        } catch (JsonGenerationException e) {
            
            return null;
        } catch (JsonMappingException e2) {
           
            return null;
        } catch (IOException e3) {
            
            return null;
        }
    }
    
    public static String getHttpPostOrPut(String jsonRequestObject2,HttpEntityEnclosingRequestBase httpreq) {
        String result = "";        
        try {
            HttpClient httpclient = new DefaultHttpClient();            
            httpreq.setEntity(new StringEntity(jsonRequestObject2.toString(), "UTF-8"));
            httpreq.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpreq.setHeader("Content-type", "application/json");
            HttpResponse httpResponse = httpclient.execute(httpreq);
            
            InputStream inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
                inputStream.close();
            }
          
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
               
            	throw new HttpException();
            }
           
            return result;
        } catch (Exception e) {
            
            return "Did not work!";
        }
    }
    
    public static String getHttpDelete(String jsonRequestObject2,HttpDelete httpreq) {
    	String response2 = "";
        try {
            
            HttpResponse Httpresponse = new DefaultHttpClient().execute(httpreq);
            if (Httpresponse.getStatusLine().getStatusCode() != 200) {
            	throw new HttpException();
            }
            HttpEntity entity = Httpresponse.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                response2 = convertInputStreamToString(instream);
                instream.close();
            }
            return response2;
        } catch (Exception e) {
            
            return "Did not work!";
        }
    }
    
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String result = "";
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                result = result + line;
            } else {
                inputStream.close();
                return result;
            }
        }
    }
    
    public static String getHttpResponse(HttpGet httpget) throws Exception {
        String response2 = "";
        try {
            
            HttpResponse Httpresponse = new DefaultHttpClient().execute(httpget);
            if (Httpresponse.getStatusLine().getStatusCode() != 200) {
                throw new HttpException();
            }
            HttpEntity entity = Httpresponse.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                response2 = convertInputStreamToString(instream);
                instream.close();
            }
            return response2;
        } catch (Exception e) {
            
            return "Did not work!";
        }
    }
}
