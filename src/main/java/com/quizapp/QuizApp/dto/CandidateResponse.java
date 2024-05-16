package com.quizapp.QuizApp.dto;

public class CandidateResponse {

    private Integer id;
    private String response;

    public CandidateResponse() {
    }

    public CandidateResponse(Integer id, String response) {
        this.id = id;
        this.response = response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "CandidateResponse{" +
                "id=" + id +
                ", response='" + response + '\'' +
                '}';
    }
}
