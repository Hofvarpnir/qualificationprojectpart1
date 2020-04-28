package com.javamaster.entity;

import org.json.JSONObject;
import org.json.*;
import javax.persistence.*;

@Entity
@Table(name = "tests")
public class Tests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String question;

    @Column
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerByMetric(Long answer_part){
        String jsonBlocks;
        String answer = "";
        try{
            JSONObject obj = new JSONObject(this.getAnswer());
            int block_counts = obj.getJSONObject("blockCounts").getInt("count");
            int count = 0;
            int metric = 0;

            for (int i = 0; i < block_counts; i++)
            {
                count = i + 1;
                jsonBlocks = "block_" + count;
                metric = obj.getJSONObject(jsonBlocks).getInt("metric");
                if (metric > answer_part)
                    answer += "\n";
                else
                    answer += obj.getJSONObject(jsonBlocks).getString("block");
            }
            return answer;
        }
        catch(org.json.JSONException exception){
            return "json error";
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((question == null) ? 0 : question.hashCode());
        result = prime * result + ((answer == null) ? 0 : answer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tests other = (Tests) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (question == null) {
            if (other.question != null)
                return false;
        } else if (!question.equals(other.question))
            return false;
        if (answer == null) {
            if (other.answer != null)
                return false;
        } else if (!answer.equals(other.answer))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Users [id=" + id + ", question=" + question + ", answer=" + answer + "]";
    }

}
//getters and setters
