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
package ca.uwaterloo.magic.goodhikes;

import android.app.Application;

import ca.uwaterloo.magic.goodhikes.data.User;
import ca.uwaterloo.magic.goodhikes.data.UserManager;

public class GoodHikesApplication extends Application {
    public User currentUser;
    private UserManager userManager;

    public void onCreate() {
        super.onCreate();
        userManager = new UserManager(getApplicationContext());
        currentUser = userManager.getUser();
    }
    public void setUser(User user) {
        this.currentUser = user;
    }
}
