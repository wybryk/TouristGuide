package pl.touristguide.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.touristguide.common.HashUtils;
import pl.touristguide.model.Account;
import pl.touristguide.model.UserDetail;
import pl.touristguide.springapp.dao.AccountDao;
import pl.touristguide.springapp.dao.UserDetailDao;
import pl.touristguide.springapp.dto.AccountDTO;
import pl.touristguide.springapp.dto.UserDetailDTO;
import pl.touristguide.springapp.mapper.UserMapper;

import java.util.Optional;

@Service
public class UserService {
    private final AccountDao accountDao;
    private final UserDetailDao userDetailDao;

    @Autowired
    public UserService(AccountDao accountDao, UserDetailDao userDetailDao) {
        this.accountDao = accountDao;
        this.userDetailDao = userDetailDao;
    }

    public UserDetailDTO registration(UserDetailDTO userDetailDTO) throws Exception {
        UserDetail userDetail = userDetailDao.save(UserMapper.toUserDetail(userDetailDTO));
        System.out.println(userDetail);
        AccountDTO accountDTO = userDetailDTO.getAccount();
        accountDTO.setPassword(HashUtils.generateHash(accountDTO.getPassword(),  10));
        System.out.println(accountDTO.getPassword());
        Account account = accountDao.save(UserMapper.toAccount(accountDTO, userDetail));
        System.out.println(account);
        return UserMapper.toUserDTO(account);
    }

    public UserDetailDTO login(AccountDTO accountDTO) throws Exception {
        Account modelAccount = findAccount(accountDTO);

        if (HashUtils.verifyPassword(accountDTO.getPassword(), modelAccount.getPassword())) {
            return UserMapper.toUserDTO(modelAccount);
        } else {
            throw new Exception(modelAccount.getLogin());
        }
    }

    private Account findAccount(AccountDTO accountDTO) throws Exception {
        Optional<Account> account = accountDao.findAccountByLogin(accountDTO.getLogin());
        if (account.get() == null) {
            throw new Exception(Account.class.getSimpleName());
        }

        return account.get();
    }

    public void updateUserAccount(Long userDetailId, UserDetailDTO userDetailDTO) {

    }

    public void deleteUserAccount(Long userDetailId) {
    }
}
