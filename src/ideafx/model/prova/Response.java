
package ideafx.model.prova;

import java.io.InputStream;

/**
 *
 * @author flo
 */
public class Response {
    private InputStream body;

    public Response(InputStream body) {
        this.body = body;
    }

    public InputStream getBody() {
        return body;
    }
}
