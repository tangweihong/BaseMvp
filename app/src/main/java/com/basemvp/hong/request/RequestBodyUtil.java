/**
 * Copyright (C) 2018 Hong(tangweihong1069@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 　　　　http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basemvp.hong.request;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * RequestBodyUtil
 * Created by Lokiy on 2016/10/12.
 * Version:1
 */
@SuppressWarnings("WeakerAccess")
public class RequestBodyUtil {
	public static final String FILE = "file\"; filename=\"image.jpg";

	public static String getFileName(int index){
		return String.format(Locale.CHINA, "file%d\"; filename=\"image%d.jpg", index, index);
	}
	public static RequestBody getImageRequestBody(Bitmap bitmap) {
		if (bitmap == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
		return RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
	}

	public static MultipartBody.Part getPart(String name, String value) {
		Headers headers = Headers.of("Content-Disposition", "form-data; name=\"" + name + "\"", "Content-Transfer-Encoding", "binary");
		return MultipartBody.Part.create(headers, getTextRequestBody(value));
	}

	public static RequestBody getTextRequestBody(String text) {
		return RequestBody.create(MediaType.parse("text/plain"), text);
	}

	public static String getPartName(MultipartBody.Part part){
		try {
			Field field = MultipartBody.Part.class.getDeclaredField("headers");
			field.setAccessible(true);
			Headers partHeaders = (Headers) field.get(part);
			return partHeaders.value(0).replace("form-data; name=", "").replace("\"", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getPartTextValue(MultipartBody.Part part){
		try {
			Field field = MultipartBody.Part.class.getDeclaredField("body");
			field.setAccessible(true);
			RequestBody body = (RequestBody) field.get(part);
			Buffer buffer = new Buffer();
			body.writeTo(buffer);
			MediaType contentType = body.contentType();
			if (contentType != null && "text".equals(contentType.type())) {
				Charset charset = contentType.charset(Charset.forName("UTF-8"));
				return buffer.readString(charset);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
