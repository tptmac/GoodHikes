/*------------------------------------------------------------------------------
 *   Authors: Slavik, George, Thao, Chelsea
 *   Copyright: (c) 2016 Team Magic
 *
 *   This file is part of GoodHikes.
 *
 *   GoodHikes is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   GoodHikes is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with GoodHikes.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.uwaterloo.magic.goodhikes.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.android.gms.maps.model.LatLng;

import java.io.ByteArrayOutputStream;

import ca.uwaterloo.magic.goodhikes.data.RoutesContract.MilestoneEntry;

public class Milestone {
    private long id;
    private LatLng latLng;
    private String note;
    private Bitmap image;

    public Milestone() {}
    public Milestone(LatLng latLng, String note, Bitmap image) {
        this.latLng = latLng;
        this.note = note;
        this.image = image;
    }

    public long getId() {return id;}
    public void setId(long id) {this.id = id;}

    public LatLng getLatLng() { return latLng; }
    public void setLatLng(LatLng latLng) { this.latLng = latLng; }

    public String getNote() { return note; }
    public void setNote(String note){ this.note = note; }

    public Bitmap getImage() { return image; }
    public void setImage(Bitmap image){
        this.image = image;
    }

    public static Milestone fromDBCursor(Cursor cursor){
        Milestone milestone = new Milestone();
        milestone.setId(cursor.getLong(cursor.getColumnIndex(MilestoneEntry._ID)));

        double latitude = cursor.getDouble(cursor.getColumnIndex(MilestoneEntry.COLUMN_COORD_LAT));
        double longitude = cursor.getDouble(cursor.getColumnIndex(MilestoneEntry.COLUMN_COORD_LONG));
        milestone.setLatLng(new LatLng(latitude, longitude));

        milestone.setNote(cursor.getString(cursor.getColumnIndex(MilestoneEntry.COLUMN_NOTE)));

        byte[] image = cursor.getBlob(cursor.getColumnIndex(MilestoneEntry.COLUMN_IMAGE));
        milestone.setImage(BitmapFactory.decodeByteArray(image, 0, image.length));

        return milestone;
    }

    public ContentValues toContentValues(){
        ContentValues values = new ContentValues();
        values.put(MilestoneEntry.COLUMN_COORD_LAT,     this.latLng.latitude);
        values.put(MilestoneEntry.COLUMN_COORD_LONG,    this.latLng.longitude);
        values.put(MilestoneEntry.COLUMN_NOTE,          this.note);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (image != null) {
            this.image.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        }
        values.put(MilestoneEntry.COLUMN_IMAGE,         stream.toByteArray());

        return values;
    }
}
