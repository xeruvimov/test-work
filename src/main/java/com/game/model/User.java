package com.game.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Double ratio;

    private Integer numberOfGame;

    @Transient
    private Map<String, String> tried;

    @Transient
    private List<Integer> currentNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Map<String, String> getTried() {
        return tried;
    }

    public void setTried(Map<String, String> tried) {
        this.tried = tried;
    }

    public List<Integer> getCurrentNumber() {
        return currentNumber;
    }

    public void setCurrentNumber(List<Integer> currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Integer getNumberOfGame() {
        return numberOfGame;
    }

    public void setNumberOfGame(Integer numberOfGame) {
        this.numberOfGame = numberOfGame;
    }

    public void startGame() {
        if (currentNumber == null) {
            Random rnd = new Random();
            List<Integer> numb = new LinkedList<>();
            currentNumber = new LinkedList<>();

            for (int i = 0; i < 10; i++)
                numb.add(i);

            for (int i = 0; i < 4; i++) {
                int position = rnd.nextInt(4);
                currentNumber.add(numb.get(position));
                numb.remove(position);
            }
        }

        if (tried == null)
            tried = new LinkedHashMap<>();
    }

    public boolean trying(List<Integer> number) {
        int[] res = {0, 0};

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (currentNumber.get(i).equals(number.get(j))) {
                    if (i == j)
                        res[0]++;
                    else
                        res[1]++;
                    break;
                }

        StringBuilder curTry = new StringBuilder();
        for (Integer integer : number) {
            curTry.append(integer);
        }
        String result = res[0] + " Bulls " + res[1] + " Cows";

        tried.put(curTry.toString(), result);

        return res[0] == 4;
    }

    public void endGame() {
        if (numberOfGame == null) numberOfGame = 1;
        else numberOfGame++;

        if (ratio == null) ratio = (double) tried.size();
        else ratio = (ratio + (double) tried.size()) / (double) numberOfGame;

        currentNumber = null;
        tried = null;
    }
}
