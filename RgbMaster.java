package task;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class RgbMaster {
    public boolean hasAlphaChannel;     //есть ли альфа-канал
    private BufferedImage image;        //изображение
    private int width;                  //ширина
    private int height;                 //высота
    private int[] colors;               //массив RGB-пикселей

    RgbMaster(String path) throws IOException {
        image = ImageIO.read(new File(path));           //считываем изображение
        width = image.getWidth();                       //получаем ширину
        height = image.getHeight();                     //получаем высоту
        colors = image.getRGB(                          //получаем RGB изображения
                0,                                //нач. координата X
                0,                                //нач. координата Y
                width,                                  //ширина
                height,                                 //высота
                null,                           //если не null, то пиксели rgb записываются сюда
                0,                                //смещение в массиве rgbArray
                width * height                  //размер строки сканирования для rgbArray
        );
        hasAlphaChannel = image.getAlphaRaster() != null;   //есть ли альфа-канал
    }

    static float[] rgbIntToArray(int rgbInt) {          //метод для преобразования rgb int в rgb массив
        Color color = new Color(rgbInt);                //экземпляр класса Color, передаем цвет
        return color.getRGBComponents(null);   //получаем компоненты [red, green, blue, alpha]
    }

    static int rgbArrayToInt(float[] rgbArray) throws Exception {     //rgb массив в rgb int
        if (rgbArray.length == 3) {                                   //если нет альфа-канала
            return new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();   //возвращаем цвет rgb
        } else if (rgbArray.length == 4) {                            //если есть альфа-канал
            return new Color(rgbArray[0], rgbArray[1], rgbArray[2], rgbArray[3]).getRGB();  //возвращаем цвет rgba
        } else {                                                      //если не rgb и не rgba
            throw new Exception("invalid color");                     //кидаем invalid color
        }
    }

    void changeImage(ImageOperation operation) throws Exception {     //метод меняет изображение, принимает лямбду
        for (int i = 0; i < colors.length; i++) {                     //пробегаемся по rgb массиву
            colors[i] = operation.execute(colors[i]);                 //для каждого пикселя применяем операцию
        }
    }

    void iterateInImage(ImageIteratorCallback operation) {            //метод для итерации по изображению
        for (int color : colors) {                                    //для каждого rgb int в rgb массиве
            operation.callback(color);                                //TODO callback - не понимаю этот момент
        }
    }

    void save(String fileName) throws IOException {                   //метод для сохранения изображения
        int type = hasAlphaChannel ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;//если есть альфа то тип rgba
        image = new BufferedImage(width, height, type);               //создаем изображение
        image.setRGB(0, 0, width, height, colors, 0, width * height);   //устанавливаем rgb
        ImageIO.write(image, "png", new File(fileName));   //пишем изображение
    }
}
