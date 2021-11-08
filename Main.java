package task;

public class Main {
    public static void main(String[] args) throws Exception {
        RgbMaster rgbMaster = new RgbMaster("src/img.png");
        ImageFunctionsImpl impl = new ImageFunctionsImpl();
        rgbMaster.changeImage(impl::grayScale);
        rgbMaster.save("src/out_img.png");
    }
}
