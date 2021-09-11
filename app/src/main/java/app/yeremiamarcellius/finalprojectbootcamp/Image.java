package app.yeremiamarcellius.finalprojectbootcamp;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable{
    private String desc, name, image;

    public Image(String image, String desc, String name) {
        this.image = image;
        this.desc = desc;
        this.name = name;
    }

    public Image(){}

    protected Image(Parcel in) {
        image = in.readString();
        desc = in.readString();
        name = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public String getImage() {
        return image;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(desc);
        dest.writeString(name);
    }
}
