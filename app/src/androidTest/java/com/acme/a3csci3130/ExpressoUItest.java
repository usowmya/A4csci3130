package com.acme.a3csci3130;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.ListView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by Sowmya Umesh on 3/13/2018.
 */

public class ExpressoUItest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void test(){
        createTest();
        updateTest();
        readtest();
        deletetest();
    }

    /**
     * Pre requiste: there should not be any data in the firebase table
     */

    /**
     * Checks for the creation of the contact 'Sowmya'
     */

    public void createTest() {
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.name)).perform(typeText("Sowmya"),closeSoftKeyboard());
        onView(withId(R.id.businessnumber)).perform(typeText("98765"),closeSoftKeyboard());
        onView(withId(R.id.primarybusiness)).perform(typeText("Fisher"),closeSoftKeyboard());
        onView(withId(R.id.address)).perform(typeText("110 apt halifax"), closeSoftKeyboard());
        onView(withId(R.id.province)).perform(typeText("NS"),closeSoftKeyboard());
        onView(withId(R.id.submitButton)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));


    }

    /**
     * It checks for the update data from 'Sowmya' to 'Anil'
     */

    public void updateTest(){
        //onView(withId(R.id.listView));
        SystemClock.sleep(2000);// waits for the listview to load
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());//checks for the first position in list view
        onView(withId(R.id.name)).perform(replaceText("Anil"),closeSoftKeyboard());
        onView(withId(R.id.updateButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).check(matches(withText("Anil")));


    }

    /**
     * Checks the reading of data from the database
     */


    public void readtest(){
        SystemClock.sleep(2000);// waits for the listview to load
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());//checks for the first position in list view
        onView(withId(R.id.name)).check(matches(withText("Anil")));
        onView(withId(R.id.businessnumber)).check(matches(withText("98765")));
        onView(withId(R.id.primarybusiness)).check(matches(withText("Fisher")));
        onView(withId(R.id.address)).check(matches(withText("110 apt halifax")));
        onView(withId(R.id.province)).check(matches(withText("NS")));
        pressBack();
    }

    /**
     * Checks for the deletion of the contact from the listview
     */


    public void deletetest() {
        SystemClock.sleep(2000);// waits for the listview to load
        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());//checks for the first position in list view
        onView(withId(R.id.deleteButton)).perform(click()); //removes the contact
        onView(withId(R.id.listView)).check(matches(not(isDisplayed())));//checks nothing is in the list view
    }


}

