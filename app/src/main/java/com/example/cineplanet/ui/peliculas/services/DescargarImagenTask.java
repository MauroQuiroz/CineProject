package com.example.cineplanet.ui.peliculas.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cineplanet.ui.peliculas.daos.MovieDAO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

public class DescargarImagenTask  extends AsyncTask<String, Void, byte[]> {
    private Context context;
    private int id;
    private MovieDAO movieDao;
    private boolean type;
    public DescargarImagenTask(Context context,int id,boolean type) {
        this.context = context;
        this.id = id;
        this.type = type;
        AppDatabase db = AppDatabase.getInstance(context);
        movieDao = db.movieDAO();
    }

    @Override
    protected byte[] doInBackground(String... urls) {
        String urlImagen = urls[0];
        byte[] datosImagen = null;

        try {
            Log.i("DescargarImagenTask", "Descargando imagen de URL: " + urlImagen);

            URL imageUrl = new URL(urlImagen);
            HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
            conn.setInstanceFollowRedirects(true);  // Seguir redireccionamientos automáticamente
            conn.connect();

            // Verificar el código de respuesta
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                Log.e("DescargarImagenTask", "Error en la conexión, código de respuesta: " + responseCode);
                return null;
            }

            // Verificar el tipo de contenido
            String contentType = conn.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                Log.e("DescargarImagenTask", "Tipo de contenido no es una imagen: " + contentType);
                return null;
            }

            // Leer la imagen como un InputStream
            InputStream inputStream = conn.getInputStream();

            // Convertir InputStream a un array de bytes
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }

            // Convertir a byte array
            datosImagen = output.toByteArray();

            // Cierra los streams
            output.close();
            inputStream.close();

            Log.i("DescargarImagenTask", "Imagen descargada, tamaño en bytes: " + datosImagen.length);

        } catch (Exception e) {
            Log.e("DescargarImagenTask", "Error al descargar la imagen", e);
        }

        return datosImagen;
    }

    @Override
    protected void onPostExecute(byte[] datosImagen) {
        super.onPostExecute(datosImagen);

        if (datosImagen != null) {
            // Validar que la imagen sea decodificable antes de guardar
            Bitmap bitmap = BitmapFactory.decodeByteArray(datosImagen, 0, datosImagen.length);
            if (bitmap != null) {

                Movie movie = movieDao.getMovieById(this.id);
                if(this.type){
                    movie.setDatosImagen(datosImagen);
                    movieDao.update(movie);
                }else{
                    movie.setDatosImagenMini(datosImagen);
                    movieDao.update(movie);
                }


                Log.e("DescargarImagenTask", "La decodificación de la imagen Correct."+22);

            } else {
                Log.e("DescargarImagenTask", "La decodificación de la imagen falló.");
            }
        } else {
            Log.e("DescargarImagenTask", "Datos de imagen nulos o vacíos.");
        }
    }
}
