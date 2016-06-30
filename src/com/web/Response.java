package com.web;

public class Response<T> {
    private T neuralResponse;
    private T fuzzyResponse;

    public Response(T neuralResponse, T fuzzyResponse) {
        this.neuralResponse = neuralResponse;
        this.fuzzyResponse = fuzzyResponse;
    }

    public T getNeuralResponse() {
        return neuralResponse;
    }

    public void setNeuralResponse(T neuralResponse) {
        this.neuralResponse = neuralResponse;
    }

    public T getFuzzyResponse() {
        return fuzzyResponse;
    }

    public void setFuzzyResponse(T fuzzyResponse) {
        this.fuzzyResponse = fuzzyResponse;
    }

    @Override
    public String toString() {
        return "Response{" +
                "neuralResponse=" + neuralResponse +
                ", fuzzyResponse=" + fuzzyResponse +
                '}';
    }
}
