package task;

public class ImageFunctionsImpl extends ImageFunctions{
    @Override
    int grayScale(int color) throws Exception {
        float[] arr = RgbMaster.rgbIntToArray(color);
        float gray = (arr[0]+arr[1]+arr[2])/3;
        arr[0] = gray;
        arr[1] = gray;
        arr[2] = gray;
        return RgbMaster.rgbArrayToInt(arr);
    }

    @Override
    int sepia(int color) throws Exception {
        float[] arr = RgbMaster.rgbIntToArray(color);
        arr[0] = (float) (0.393*arr[0] + 0.769*arr[1] + 0.189*arr[2]);
        arr[1] = (float) (0.349*arr[0] + 0.686*arr[1] + 0.168*arr[2]);
        arr[2] = (float) (0.272*arr[0] + 0.534*arr[1] + 0.131*arr[2]);
        return RgbMaster.rgbArrayToInt(arr);
    }

    @Override
    int inversion(int color) throws Exception {
        float[] arr = RgbMaster.rgbIntToArray(color);
        arr[0] = 1-arr[0];
        arr[1] = 1-arr[1];
        arr[2] = 1-arr[2];
        return RgbMaster.rgbArrayToInt(arr);
    }

    @Override
    int onlyRed(int color) throws Exception {
        float[] arr = RgbMaster.rgbIntToArray(color);
        arr[1] = 0;
        arr[2] = 0;
        return RgbMaster.rgbArrayToInt(arr);
    }

    @Override
    int onlyGreen(int color) throws Exception {
        float[] arr = RgbMaster.rgbIntToArray(color);
        arr[0] = 0;
        arr[2] = 0;
        return RgbMaster.rgbArrayToInt(arr);
    }

    @Override
    int onlyBlue(int color) throws Exception {
        float[] arr = RgbMaster.rgbIntToArray(color);
        arr[0] = 0;
        arr[1] = 0;
        return RgbMaster.rgbArrayToInt(arr);
    }

    @Override
    void fft(int color) {
    }
}
