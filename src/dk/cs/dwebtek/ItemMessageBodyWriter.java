package dk.cs.dwebtek;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by Morten on 05-03-2017.
 */
@Produces("application/xml")
public class ItemMessageBodyWriter implements MessageBodyWriter<Item> {

    @Override
    public boolean isWriteable(Class<?> type, Type genericType,
                               Annotation[] annotations, MediaType mediaType) {
        return type == Item.class;
    }

    @Override
    public long getSize(Item myBean, Class<?> type, Type genericType,
                        Annotation[] annotations, MediaType mediaType) {
        // deprecated by JAX-RS 2.0 and ignored by Jersey runtime
        return 0;
    }

    @Override
    public void writeTo(Item myBean,
                        Class<?> type,
                        Type genericType,
                        Annotation[] annotations,
                        MediaType mediaType,
                        MultivaluedMap<String, Object> httpHeaders,
                        OutputStream entityStream)
            throws IOException, WebApplicationException {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);

            // serialize the entity myBean to the entity output stream
            jaxbContext.createMarshaller().marshal(myBean, entityStream);
        } catch (JAXBException jaxbException) {
            throw new ProcessingException(
                    "Error serializing a MyBean to the output stream", jaxbException);
        }
    }
}



