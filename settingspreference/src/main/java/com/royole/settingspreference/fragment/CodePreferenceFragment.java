package com.royole.settingspreference.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.royole.settingspreference.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CodePreferenceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CodePreferenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CodePreferenceFragment extends PreferenceFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CodePreferenceFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment XmlPreferenceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CodePreferenceFragment newInstance(String param1, String param2) {
        CodePreferenceFragment fragment = new CodePreferenceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //addPreferencesFromResource(R.xml.xmlpreference);
        //Context context = getPreferenceManager().getContext();

        PreferenceScreen rootPreference = getPreferenceManager().createPreferenceScreen(getActivity());
        setPreferenceScreen(rootPreference);


        populatePreferenceHierarchy(rootPreference);

    }

//    @Override
//    public void onCreatePreferences(Bundle bundle, String s) {
//
//    }



    private void populatePreferenceHierarchy(PreferenceScreen root) {
        PreferenceCategory inlinePrefs = new PreferenceCategory(getActivity());
        inlinePrefs.setTitle("In-line Preference");
        root.addPreference(inlinePrefs);

        CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getActivity());
        checkBoxPreference.setTitle("Checkbox preference");
        checkBoxPreference.setSummary("This is a checkbox");
        inlinePrefs.addPreference(checkBoxPreference);



        PreferenceCategory dialogbasePrefs = new PreferenceCategory(getActivity());
        dialogbasePrefs.setTitle("Dialog-base Preferences");

        root.addPreference(dialogbasePrefs);
        EditTextPreference editTextPreference = new EditTextPreference(getActivity());
        editTextPreference.setTitle("Edit text preference");
        editTextPreference.setSummary("An example that users can edit text dialog");
        dialogbasePrefs.addPreference(editTextPreference);
        ListPreference listPreference = new ListPreference(getActivity());
        listPreference.setKey("code_preference_list");
        listPreference.setTitle("List Preference");
        listPreference.setSummary("An example that uses a list dialog");
        listPreference.setEntries(R.array.xml_pref_list_entry);
        listPreference.setEntryValues(R.array.xml_pref_list_entry_value);
        listPreference.setDefaultValue("2");
        dialogbasePrefs.addPreference(listPreference);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_xml_preference, container, false);

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
