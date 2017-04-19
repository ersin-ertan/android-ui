package com.nullcognition.litho;

import android.app.Application;
import com.facebook.soloader.SoLoader;

/**
 * Created by Ersin Ertan on 4/19/17.
 *
 * // // #  Copyright (c) 2006.The MMSoftware Center
 * // #                           MMSoftware. All Rights Reserved.
 * // #
 * // #  Permission to use, copy, modify, and distribute this software and its
 * // #  documentation for any purpose, without fee, and without a written
 * // #  agreement, is here by granted, provided that the above copyright notice,
 * // #  this paragraph and the following two paragraphs appear in all copies,
 * // #  modifications, and distributions.  Created by:
 * // #
 * // #                        M.M.S.O.F.T.W.A.R.E
 * // #
 * // #  Department of MMSoftware
 * // #  Level 4, Building H2, 196 Hoang Dieu St, Ward 8, District 4, HCM, Vietnam
 * // #  For technical information, contact mmsoftvn@mmsofts.com
 * // #
 * // #  IN NO EVENT SHALL REGENTS BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT,
 * // #  SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS,
 * // #  ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 * // #  REGENTS HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * // #
 * // #  REGENTS SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 * // #  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * // #  PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF
 * // #  ANY, PROVIDED HEREUNDER IS PROVIDED "AS IS". REGENTS HAS NO OBLIGATION
 * // #  TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR
 * // #  MODIFICATIONS.
 * //
 */

public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();

    SoLoader.init(this, false);
  }
}
