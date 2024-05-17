package com.zm.a1.model;

import lombok.*;

@Data
@RequiredArgsConstructor
public class Country {
    @NonNull
    private String name;
    private String capital;
    private String iso2;
    private double population;
    private double pop_growth;
    private Currency currency;

    @Data
    public static class Currency {
        private String code;
        private String name;
    }
}
