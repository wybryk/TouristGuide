package pl.touristguide.springapp.mapper;

import pl.touristguide.model.Account;
import pl.touristguide.model.UserDetail;
import pl.touristguide.springapp.dto.AccountDTO;
import pl.touristguide.springapp.dto.UserDetailDTO;

public class UserMapper {

    @Deprecated
    public static UserDetailDTO toUserDTO(UserDetail userDetail) {
        UserDetailDTO userDTO = new UserDetailDTO();
        userDTO.setUserDetailId(userDetail.getUserDetailId());
        userDTO.setName(userDetail.getName());
        userDTO.setEmail(userDetail.getEmail());
        userDTO.setAvatar(userDetail.getAvatar());
        userDTO.setAccount(toAccountDTO(userDetail.getAccount()));
        return userDTO;
    }

    public static UserDetailDTO toUserDTO(Account account) {
        UserDetailDTO userDTO = new UserDetailDTO();
        userDTO.setUserDetailId(account.getUserDetail().getUserDetailId());
        userDTO.setName(account.getUserDetail().getName());
        userDTO.setEmail(account.getUserDetail().getEmail());
        userDTO.setAvatar(account.getUserDetail().getAvatar());
        userDTO.setAccount(toAccountDTO(account));
        return userDTO;
    }

    private static AccountDTO toAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setAccountId(account.getAccountId());
        accountDTO.setLogin(account.getLogin());
        return accountDTO;
    }

    public static UserDetail toUserDetail(UserDetailDTO userDetailDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserDetailId(userDetailDTO.getUserDetailId());
        userDetail.setName(userDetailDTO.getName());
        userDetail.setEmail(userDetailDTO.getEmail());
        userDetail.setAvatar(userDetailDTO.getAvatar());
        return userDetail;
    }

    public static Account toAccount(AccountDTO accountDTO, UserDetail userDetail) {
        Account account = new Account();
        account.setAccountId(accountDTO.getAccountId());
        account.setLogin(accountDTO.getLogin());
        account.setPassword(accountDTO.getPassword());
        account.setUserDetail(userDetail);
        return account;
    }

    public static Account toAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountId(accountDTO.getAccountId());
        account.setLogin(accountDTO.getLogin());
        account.setPassword(accountDTO.getPassword());
        return account;
    }
}
