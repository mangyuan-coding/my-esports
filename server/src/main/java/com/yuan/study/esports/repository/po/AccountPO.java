package com.yuan.study.esports.repository.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_account")
public class AccountPO {

    @TableId
    private Long id;

    private String username;
    private String password;
    private String email;

    private Boolean isAdmin;
}
