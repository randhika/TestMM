// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.codehaus.jackson.jaxrs;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import org.codehaus.jackson.JsonParseException;

public class JsonParseExceptionMapper
    implements ExceptionMapper
{

    public JsonParseExceptionMapper()
    {
    }

    public volatile Response toResponse(Throwable throwable)
    {
        return toResponse((JsonParseException)throwable);
    }

    public Response toResponse(JsonParseException jsonparseexception)
    {
        return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).entity(jsonparseexception.getMessage()).type("text/plain").build();
    }
}
