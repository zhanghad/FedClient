package com.fedclient.util;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class MyObjectInputStream extends ObjectInputStream {

    private static final String TAG = "MyObjectInputStream";

    protected MyObjectInputStream() throws IOException, SecurityException {
        super();
    }

    public MyObjectInputStream(InputStream arg0) throws IOException {
        super(arg0);
        Log.i(TAG, "MyObjectInputStream: ");
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException{
        Log.i(TAG, "resolveClass: start");

        String name = desc.getName();
        try {

            if(name.startsWith("com.fedserver.common.util"))
                name = name.replace("com.fedserver.common.util.Message", "com.fedclient.fed.message.Message");
            Log.d(TAG, "resolveClass: "+name);
            return Class.forName(name);

        } catch (ClassNotFoundException ex) {
            Log.e(TAG, "resolveClass: "+ex);
            ex.printStackTrace();
        }


        return super.resolveClass(desc);
    }
}
