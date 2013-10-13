package com.etech.system.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCompress {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("test.zip");
		//file.setWritable(true);
		//file.createNewFile();
		FileOutputStream f = new FileOutputStream(file);
		ZipOutputStream zos = new ZipOutputStream(f);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		//一个stream转writer的例子： OutputStreamWriter osw = new OutputStreamWriter(zos);
		
		for (String arg :args) {
			System.out.println("Writing file " + arg);
			
			BufferedReader in = new BufferedReader(new FileReader(arg));
			zos.putNextEntry(new ZipEntry(arg));
			int c;
			while ((c=in.read())!=-1) {
				out.write(c);
			}
			in.close();
			out.flush();
		}
		out.close();
	}

}
