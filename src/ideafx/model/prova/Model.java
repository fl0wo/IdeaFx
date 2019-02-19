package ideafx.model.prova;

import com.google.gson.Gson;
import ideafx.model.prova.jsoninfos.JsonResult;
import ideafx.model.prova.jsoninfos.Project;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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
public class Model {

    private final String API_KEY  = "RaSX1GQZVvH1vjpod5TuFUnta9Ars9Xf";
    private final String API_URL  = "https://www.behance.net/v2/projects?api_key="+API_KEY;
    
    private ArrayList<Project> projects;
    
    // Have one (or more) threads ready to do the async tasks. Do this during startup of your app.
    private ExecutorService executor;
    
    public Model(){
        executor = Executors.newFixedThreadPool(1); 
    }
    
    public void fireRequest() throws InterruptedException, UnsupportedEncodingException, ExecutionException, MalformedURLException {
        
        // Fire a request.
        Future<Response> response = executor.submit(new Request(new URL(API_URL)));
        
        // Get the response (here the current thread will block until response is returned).
        InputStream in = response.get().getBody();
        Reader reader = new InputStreamReader(in, "UTF-8");
        JsonResult result  = new Gson().fromJson(reader, JsonResult.class);
        
        projects = result.getProjects();
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }
    
    public void execute(Runnable r){
        executor.execute(r);
    }
    
    public void shutDown(){
        // Shutdown the threads during shutdown of your app.
        executor.shutdown();
    }

}
