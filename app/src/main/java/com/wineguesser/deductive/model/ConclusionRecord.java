package com.wineguesser.deductive.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

import androidx.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConclusionRecord implements Parcelable {

    @Exclude
    private String conclusionId;
    @Exclude
    private String userId;

    private String actualLabel;
    private String actualVariety;
    private String actualCountry;
    private String actualRegion;
    private String actualQuality;
    private Integer actualVintage;

    private String userConclusionVariety;
    private String userConclusionCountry;
    private String userConclusionRegion;
    private String userConclusionQuality;
    private Integer userConclusionVintage;

    private String appConclusionVariety;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(conclusionId);
        dest.writeString(userId);
        dest.writeString(actualLabel);
        dest.writeString(actualVariety);
        dest.writeString(actualCountry);
        dest.writeString(actualRegion);
        dest.writeString(actualQuality);
        dest.writeInt(actualVintage);
        dest.writeString(userConclusionVariety);
        dest.writeString(userConclusionCountry);
        dest.writeString(userConclusionRegion);
        dest.writeString(userConclusionQuality);
        dest.writeInt(userConclusionVintage);
        dest.writeString(appConclusionVariety);
    }

    public static final Creator<ConclusionRecord> CREATOR = new Creator<ConclusionRecord>() {
        @Override
        public ConclusionRecord createFromParcel(Parcel in) {
            return new ConclusionRecord(in);
        }

        @Override
        public ConclusionRecord[] newArray(int size) {
            return new ConclusionRecord[size];
        }
    };

    public ConclusionRecord(Parcel in) {
        this.conclusionId = in.readString();
        this.userId = in.readString();
        this.actualLabel = in.readString();
        this.actualVariety = in.readString();
        this.actualCountry = in.readString();
        this.actualRegion = in.readString();
        this.actualQuality = in.readString();
        this.actualVintage = in.readInt();
        this.userConclusionVariety = in.readString();
        this.userConclusionCountry = in.readString();
        this.userConclusionRegion = in.readString();
        this.userConclusionQuality = in.readString();
        this.userConclusionVintage = in.readInt();
        this.appConclusionVariety = in.readString();
    }
}
