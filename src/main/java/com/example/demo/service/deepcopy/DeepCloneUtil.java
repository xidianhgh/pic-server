package com.example.demo.service.deepcopy;

import org.springframework.util.SerializationUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DeepCloneUtil<T> {


    public  T deepClone(T object){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(object);

            ByteArrayInputStream bais = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            ObjectInputStream objectInputStream = new ObjectInputStream(bais);

            return (T) objectInputStream.readObject();

        }catch (Exception e){
            return null;
        }
    }

    public  T deepClone2(T object){
        try {
            byte[] serialize = SerializationUtils.serialize(object);
            Object obj= SerializationUtils.deserialize(serialize);
            return (T)obj;
        }catch (Exception e){
            return null;
        }
    }

}
