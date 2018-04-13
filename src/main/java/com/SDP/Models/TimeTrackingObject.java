package com.SDP.Models;

import java.util.List;

public class TimeTrackingObject {

    private List<List<Integer>> datasets;
    private List<List<String>> datalabels;

    public List<List<Integer>> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<List<Integer>> datasets) {
        this.datasets = datasets;
    }

    public List<List<String>> getDatalabels() {
        return datalabels;
    }

    public void setDatalabels(List<List<String>> datalabels) {
        this.datalabels = datalabels;
    }
}
