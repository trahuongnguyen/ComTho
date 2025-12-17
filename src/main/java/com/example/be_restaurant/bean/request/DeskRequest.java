package com.example.be_restaurant.bean.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DeskRequest {
    String name;
    Long floorId;
}
