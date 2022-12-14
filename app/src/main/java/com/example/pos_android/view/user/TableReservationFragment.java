package com.example.pos_android.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alan.alansdk.AlanCallback;
import com.alan.alansdk.button.AlanButton;
import com.alan.alansdk.events.EventCommand;
import com.example.pos_android.R;
import com.example.pos_android.adapter.DateSelectionAdapter;
import com.example.pos_android.adapter.TableAdapter;
import com.example.pos_android.adapter.TimeSelectionAdapter;
import com.example.pos_android.contracts.TableReservationContract;
import com.example.pos_android.data.model.DateModel;
import com.example.pos_android.data.model.TableInfoModel;
import com.example.pos_android.data.model.TableModel;
import com.example.pos_android.data.model.TableReservationResponse;
import com.example.pos_android.data.model.TimeModel;
import com.example.pos_android.data.preference.SessionManager;
import com.example.pos_android.databinding.FragmentReservationBinding;
import com.example.pos_android.presenter.TableReservationPresenter;
import com.example.pos_android.utils.OnItemClickListener;
import com.example.pos_android.view.BaseFragment;
import com.example.pos_android.view.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableReservationFragment extends BaseFragment implements OnItemClickListener, TableReservationContract.View, AdapterView.OnItemSelectedListener {
    String[] classicBoothNum = {"Select", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    String[] windowsSideNum = {"Select", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};
    String[] highTopNum = {"Select", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
    private FragmentReservationBinding binding;
    private List<DateModel> dateList = new ArrayList<>();
    private List<DateModel> apiDateList = new ArrayList<>();
    private List<TimeModel> timeList = new ArrayList<>();
    private List<TableModel> tableList = new ArrayList<>();
    private DateSelectionAdapter dateSelectionAdapter;
    private TimeSelectionAdapter timeSelectionAdapter;
    private TableAdapter tableAdapter;
    private int totalCount = 0;
    private TableReservationPresenter presenter;
    private Integer selectedDatePosition = null;
    private String selectedTime = null;
    private String selectedTable = null;
    private String selectedTableNum = null;
    private TableInfoModel model;
    private AlanButton alanButton;
    private AlanCallback alanCallback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TableReservationPresenter(this, requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReservationBinding.inflate(getLayoutInflater(), container, false);
        initView();
        btnClick();
        return binding.getRoot();
    }

    private void btnClick() {
        binding.ivBack.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });
        binding.countLayout.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalCount <= 7) {
                    totalCount += 1;
                    binding.countLayout.tvGuest.setText(String.valueOf(totalCount));
                    clearTableSelection();
                }
            }
        });
        binding.countLayout.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (totalCount >= 1) {
                    totalCount -= 1;
                    binding.countLayout.tvGuest.setText(String.valueOf(totalCount));
                    clearTableSelection();
                }
            }
        });
    }

    private void initView() {
        dateList.clear();
        timeList.clear();
        apiDateList.clear();
        tableList.clear();

        Date date = new Date();
        String pattern = "MMM-dd";
        // String apiPattern = "dd-MM-yyyy";
        String apiPattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        SimpleDateFormat apiDateFormat = new SimpleDateFormat(apiPattern);
        for (int i = 0; i <= 4; i++) {
            String stringDate = simpleDateFormat.format(date).toUpperCase();
            String apiDate = apiDateFormat.format(date);
            Log.d("Date", apiDate);
            dateList.add(new DateModel(stringDate));
            apiDateList.add(new DateModel(apiDate));
            date = new Date(date.getTime() + 86400000);
        }

        timeList.add(new TimeModel("10:00 AM"));
        timeList.add(new TimeModel("11:00 AM"));
        timeList.add(new TimeModel("12:00 PM"));
        timeList.add(new TimeModel("1:00 PM"));
        timeList.add(new TimeModel("2:00 PM"));
        timeList.add(new TimeModel("3:00 PM"));
        timeList.add(new TimeModel("4:00 PM"));
        timeList.add(new TimeModel("5:00 PM"));
        timeList.add(new TimeModel("6:00 PM"));
        timeList.add(new TimeModel("7:00 PM"));
        timeList.add(new TimeModel("8:00 PM"));
        timeList.add(new TimeModel("9:00 PM"));

        tableList.add(new TableModel("Classic booth"));
        tableList.add(new TableModel("Window side"));
        tableList.add(new TableModel("High top table"));


        dateSelectionAdapter = new DateSelectionAdapter(dateList, requireContext(), this);
        binding.rvDate.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvDate.setHasFixedSize(true);
        binding.rvDate.setAdapter(dateSelectionAdapter);

        timeSelectionAdapter = new TimeSelectionAdapter(timeList, requireContext(), this);
        binding.rvTime.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvTime.setHasFixedSize(true);
        binding.rvTime.setAdapter(timeSelectionAdapter);

        tableAdapter = new TableAdapter(tableList, this, requireContext());
        binding.rvTable.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        // binding.rvTable.setHasFixedSize(true);
        binding.rvTable.setAdapter(tableAdapter);
        binding.btnContinue.setOnClickListener(v -> {
         submitBtn();
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).popBackStack();
            }
        });

        alanButton = getActivity().findViewById(R.id.alan_button);

        alanCallback = new AlanCallback() {
            /// Handle commands from Alan Studio
            @Override
            public void onCommand(final EventCommand eventCommand) {
                try {
                    JSONObject command = eventCommand.getData();
                    JSONObject data = command.getJSONObject("data");
                    String commandName = data.getString("commandName");
                    //based on commandName we can perform different tasks
                    executeCommand(commandName, data);
                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                    showToast(requireContext(), e.getMessage());
                }
            }
        };
        alanButton.registerCallback(alanCallback);
    }

    @Override
    public void onItemClick(Integer position, String from,Boolean isView ) {
        if (from == "Date") {
            for (int i = 0; i < dateList.size(); i++) {
                if (i == position) {
                    dateList.get(i).setSelected(true);
                    selectedDatePosition = position;
                } else
                    dateList.get(i).setSelected(false);
            }
            dateSelectionAdapter.notifyDataSetChanged();
        } else if (from == "Time") {
            for (int i = 0; i < timeList.size(); i++) {
                if (i == position) {
                    timeList.get(i).setSelected(true);
                    selectedTime = timeList.get(i).getTime();
                } else
                    timeList.get(i).setSelected(false);
            }
            timeSelectionAdapter.notifyDataSetChanged();
        } else {
            for (int i = 0; i < tableList.size(); i++) {
                if (i == position) {
                    if (checkSeatAvailability(position)) {
                        tableList.get(i).setSelected(true);
                        selectedTable = tableList.get(i).getTable();
                        setupSpinnerData(getTableList(selectedTable));
                    } else {
                        showSnackBar(requireView(), showSeatValidation(position));
                        tableList.get(i).setSelected(false);
                    }
                } else
                    tableList.get(i).setSelected(false);
            }
            tableAdapter.notifyDataSetChanged();
        }
    }

    private boolean checkSeatAvailability(int position) {
        switch (position) {
            case 0: {
                if (totalCount >= 4 && totalCount <= 8) return true;
                break;
            }

            case 1: {
                if (totalCount >= 1 && totalCount <= 4) return true;
                break;
            }

            case 2: {
                if (totalCount >= 2 && totalCount <= 6) return true;
                break;
            }

            default:
                return false;

        }
        return false;
    }

    private String showSeatValidation(int position) {
        switch (position) {
            case 0: {
                return "Please choose between 4 and 8 guests for Classic booth";
            }
            case 1: {
                return "Please choose up to 2 guests for Window side";
            }

            case 2: {
                return "Please choose between 2 and 6 guests for High top table";
            }

            default:
                return "";
        }
    }

    private void clearTableSelection() {
        if (tableList != null) {
            for (int i = 0; i < tableList.size(); i++) {
                tableList.get(i).setSelected(false);
            }
            tableAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgressBar() {
        showLoadingDialog(requireContext());
    }

    @Override
    public void hideProgressBar() {
        hideLoadingDialog();
    }

    @Override
    public void showApiErrorWarning(String string) {
        hideLoadingDialog();
        if (string.equals(requireActivity().getResources().getString(R.string.unauthorized))) {
            SessionManager sessionManager = new SessionManager(requireContext());
            sessionManager.clear();
            showToast(requireContext(), "Session expired");
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            startActivity(intent);
            requireActivity().finishAffinity();
        } else

            showSnackBar(requireView(), string);
    }

    @Override
    public void showWarningMessage(String message) {
        showSnackBar(requireView(), message);
    }

    @Override
    public void showTableOrderSuccessResponse(TableReservationResponse response) {
        model = new TableInfoModel(String.valueOf(response.getData().getId()),
                selectedTable, String.valueOf(totalCount),
                selectedTime, apiDateList.get(selectedDatePosition).getDate()
        );
        TableReservationFragmentDirections.ActionTableReservationFragmentToFoodListFragment directions = TableReservationFragmentDirections.actionTableReservationFragmentToFoodListFragment(model);
        Navigation.findNavController(requireView()).navigate(directions);
    }

    private void setupSpinnerData(String[] list) {
        selectedTableNum = null;
        binding.spinner.setOnItemSelectedListener(this);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (!item.equals("Select")) {
            selectedTableNum = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String[] getTableList(String item) {
        switch (item) {
            case "Classic booth": {
                return classicBoothNum;
            }
            case "Window side": {
                return windowsSideNum;
            }
            case "High top table": {
                return highTopNum;
            }

            default: {
                return null;
            }
        }
    }

    private void executeCommand(String commandName, JSONObject data) {
        switch (commandName) {
            case "add_date": {
                try {
                    String title = data.getString("title");
                    int count = Integer.parseInt(title);

                    for (int i = 0; i < dateList.size(); i++) {

                        String[] str = dateList.get(i).getDate().split("-", 0);
                        int dateDay = Integer.parseInt(str[1]);
                        if (dateDay == count) {
                            for (int j = 0; j < dateList.size(); j++) {
                                if (j == i) {
                                    dateList.get(j).setSelected(true);
                                    selectedDatePosition = i;
                                } else
                                    dateList.get(j).setSelected(false);
                            }
                            dateSelectionAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                    alanButton.deactivate();

                } catch (Exception e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry, I'm unable to pic the date");
                    alanButton.deactivate();

                }
                break;

            }
            case "add_guest": {
                try {
                    String title = data.getString("title");
                    int count = Integer.parseInt(title);
                    if (count <= 7 && count >= 1) {
                        totalCount = count;
                        binding.countLayout.tvGuest.setText(String.valueOf(totalCount));
                        clearTableSelection();
                    } else {
                        alanButton.playText("I'm sorry I cant add guest more than seven");
                    }
                } catch (Exception e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry I'm unable to do this at the moment");
                }
                alanButton.deactivate();
                break;
            }
            case "add_time": {
                try {
                    String title = data.getString("title");
                    if (title.equals("10 a.m.") || title.equals("10 A.M.")) {
                        setTime(0);
                    } else if (title.equalsIgnoreCase("11 a.m.") || title.equalsIgnoreCase("11 A.M.")) {
                        setTime(1);
                    } else if (title.equalsIgnoreCase("12 p.m.") || title.equalsIgnoreCase("12 P.M.")) {
                        setTime(2);
                    } else if (title.equalsIgnoreCase("1 p.m.") || title.equalsIgnoreCase("1 P.M.")) {
                        setTime(3);
                    } else if (title.equalsIgnoreCase("2 p.m.") || title.equalsIgnoreCase("2 P.M.")) {
                        setTime(4);
                    } else if (title.equalsIgnoreCase("3 p.m.") || title.equalsIgnoreCase("3 P.M.")) {
                        setTime(5);
                    } else if (title.equalsIgnoreCase("4 p.m.") || title.equalsIgnoreCase("4 P.M.")) {
                        setTime(6);
                    } else if (title.equalsIgnoreCase("5 p.m.") || title.equalsIgnoreCase("5 P.M.")) {
                        setTime(7);
                    } else if (title.equalsIgnoreCase("6 p.m.") || title.equalsIgnoreCase("6 P.M.")) {
                        setTime(8);
                    } else if (title.equalsIgnoreCase("7 p.m.") || title.equalsIgnoreCase("7 P.M.")) {
                        setTime(9);
                    } else if (title.equalsIgnoreCase("8 p.m.") || title.equalsIgnoreCase("8 P.M.")) {
                        setTime(10);
                    } else if (title.equalsIgnoreCase("9 p.m.") || title.equalsIgnoreCase("9 P.M.")) {
                        setTime(11);
                    } else {
                        alanButton.playText("I'm sorry I'm unable to book for given time");
                    }
                    alanButton.deactivate();
                } catch (Exception e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.deactivate();
                    alanButton.playText("I'm sorry I'm unable to do this at the moment");
                }
                break;
            }
            case "add_table": {
                try {
                    String title = data.getString("title");
                    if (title.equalsIgnoreCase("classic booth") || title.equalsIgnoreCase("classic") || title.equalsIgnoreCase("booth")) {
                        setTable(0);
                    } else if (title.equalsIgnoreCase("window side") || title.equalsIgnoreCase("window") || title.equalsIgnoreCase("side")) {
                        setTable(1);
                    } else if (title.equalsIgnoreCase("high top table") || title.equalsIgnoreCase("high top") || title.equalsIgnoreCase("top table")) {
                        setTable(2);
                    } else {
                        alanButton.playText("I'm sorry I'm unable to do pick this table");
                    }
                    alanButton.deactivate();
                } catch (Exception e) {
                    alanButton.deactivate();
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry I'm unable to do this at the moment");
                }
                alanButton.deactivate();
                break;

            }
            case "move_to_next": {
               submitBtn();
                alanButton.deactivate();
                break;

            }
            case "back" : {

                try {
                    String title = data.getString("title");
                    showToast(requireContext(), title);
                    if (title.equalsIgnoreCase("go back") || title.equalsIgnoreCase("Back") || title.equalsIgnoreCase("cancel")){
                        Navigation.findNavController(binding.getRoot()).popBackStack();
                    }

                } catch (JSONException e) {
                    Log.e("AlanButton", e.getMessage());
                    alanButton.playText("I'm sorry, I'm unable to move");
                }
                alanButton.deactivate();
                break;
            }
            default: {
                alanButton.deactivate();
                break;
            }
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        alanButton.removeCallback(alanCallback);
    }

    public void setTime(int position) {
        for (int i = 0; i < timeList.size(); i++) {
            if (i == position) {
                timeList.get(i).setSelected(true);
                selectedTime = timeList.get(i).getTime();
            } else
                timeList.get(i).setSelected(false);
        }
        timeSelectionAdapter.notifyDataSetChanged();
    }

    public void setTable(int position) {
        binding.spinner.setSelection(position);
        for (int i = 0; i < tableList.size(); i++) {
            if (i == position) {
                if (checkSeatAvailability(position)) {
                    tableList.get(i).setSelected(true);
                    selectedTable = tableList.get(i).getTable();
                    setupSpinnerData(getTableList(selectedTable));
                } else {
                    showSnackBar(requireView(), showSeatValidation(position));
                    tableList.get(i).setSelected(false);
                }
            } else
                tableList.get(i).setSelected(false);
        }
        tableAdapter.notifyDataSetChanged();
    }
    public void submitBtn(){
        if (!(totalCount > 0)) {
            showSnackBar(requireView(), "Please add guest");
        } else if (selectedDatePosition == null) {
            showSnackBar(requireView(), "Please select date");
        } else if (selectedTime == null) {
            showSnackBar(requireView(), "Please select time");
        } else if (selectedTableNum == null) {
            showSnackBar(requireView(), "Please choose table preference");
        } else {
            String myDate = apiDateList.get(selectedDatePosition).getDate();
            presenter.doTableReservation(
                    myDate, selectedTime, String.valueOf(totalCount), selectedTableNum
            );
        }
    }
}