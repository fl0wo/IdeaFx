
package ideafx.model.prova;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 *
 * @author flo
 */
public class Request implements Callable<Response> {
    private URL url;

    public Request(URL url) {
        this.url = url;
    }

    @Override
    public Response call() throws Exception {
        return new Response(url.openStream());
    }
}