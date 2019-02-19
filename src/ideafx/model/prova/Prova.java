package ideafx.model.prova;

import com.google.gson.Gson;
import ideafx.model.prova.jsoninfos.JsonResult;
import ideafx.model.prova.jsoninfos.Project;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author flo
 */
public class Prova {
    public static void main(String[] args) throws MalformedURLException, InterruptedException, ExecutionException, IOException {

        String api  = "https://www.behance.net/v2/projects?api_key=RaSX1GQZVvH1vjpod5TuFUnta9Ars9Xf";
        
        // Have one (or more) threads ready to do the async tasks. Do this during startup of your app.
        ExecutorService executor = Executors.newFixedThreadPool(1); 

        // Fire a request.
        Future<Response> response = executor.submit(new Request(new URL(api)));

        // Get the response (here the current thread will block until response is returned).
        InputStream in = response.get().getBody();
        Reader reader = new InputStreamReader(in, "UTF-8");
        JsonResult result  = new Gson().fromJson(reader, JsonResult.class);
        
        ArrayList<Project> projects = result.getProjects();
        
        for (int i = 0; i < projects.size(); i++) {
            System.out.println(projects.get(i).toString());
        }

        // Shutdown the threads during shutdown of your app.
        executor.shutdown();
        
    }   
}
