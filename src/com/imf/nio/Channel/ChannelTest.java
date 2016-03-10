package com.imf.nio.Channel;


import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Description:
 * Author:lujinyong168
 * Date:16-3-10 下午2:33
 */

public class ChannelTest {
    public static void main(String[] args){
        try{

            RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt","rw");
            FileChannel inChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(48);
            int bytesRead = inChannel.read(buf);
            while(bytesRead!=-1){
                System.out.println("Read "+bytesRead);
                buf.flip();
                while (buf.hasRemaining()){
                    System.out.println((char)buf.get());
                }
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
