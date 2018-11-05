package com.community.jboss.visitingcard.maps;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.community.jboss.visitingcard.data.VisitingCard;

import java.util.LinkedList;
import java.util.Random;

/**
 * The associated ViewModel for the MapsActivity class.
 */
public class MapsActivityViewModel extends ViewModel {
    private MutableLiveData<LinkedList<VisitingCard>> mVisitingCards;

    public VisitingCard getVisitingCard(int position){
        return mVisitingCards.getValue().get(position);
    }

    public int getVisitingCardCount(){
        return mVisitingCards.getValue().size();
    }
}
