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
        validateAddAccount(userDetailDTO);
        UserDetail userDetail = userDetailDao.save(UserMapper.toUserDetail(userDetailDTO));
        AccountDTO accountDTO = userDetailDTO.getAccount();
        accountDTO.setPassword(HashUtils.generateHash(accountDTO.getPassword(), 10));
        Account account = accountDao.save(UserMapper.toAccount(accountDTO, userDetail));
        return UserMapper.toUserDTO(account);
    }

    public UserDetailDTO login(AccountDTO accountDTO) throws Exception {
        Optional<Account> optionalAccount = findAccountByLogin(accountDTO);
        Account modelAccount;

        if (optionalAccount.isPresent()) {
            modelAccount = optionalAccount.get();
        }
        else {
            throw new Exception("Niepoprawny login lub hasło.");
        }

        if (HashUtils.verifyPassword(accountDTO.getPassword(), modelAccount.getPassword())) {
            return UserMapper.toUserDTO(modelAccount);
        } else {
            throw new Exception("Niepoprawny login lub hasło.");
        }
    }

    public void updateUserAccount(Long userDetailId, UserDetailDTO userDetailDTO) throws Exception {
        validateUpdatedAccount(userDetailDTO);
        userDetailDTO.setUserDetailId(userDetailId);
        AccountDTO accountDTO = userDetailDTO.getAccount();
        accountDTO.setPassword(HashUtils.generateHash(accountDTO.getPassword(), 10));
        UserDetail userDetail = UserMapper.toUserDetail(userDetailDTO);
        this.userDetailDao.save(userDetail);
    }

    public void deleteUserAccount(Long userDetailId) {
        userDetailDao.deleteById(userDetailId);
    }

    private void validateAddAccount(UserDetailDTO userDetailDTO) throws Exception {
        if (findAccountByLogin(userDetailDTO.getAccount()).isPresent()) {
            throw new Exception("Konto o podanym loginie już istnieje.");
        }

        if (findUserDetailByEmail(userDetailDTO).isPresent()) {
            throw new Exception("Istnieje już konto przypisane do podanego adresu email.");
        }
    }

    private void validateUpdatedAccount(UserDetailDTO userDetailDTO) throws Exception {
        Account modelAccount = findAccountById(userDetailDTO.getAccount().getAccountId());

        if (!HashUtils.verifyPassword(userDetailDTO.getAccount().getCurrentPassword(), modelAccount.getPassword())) {
            throw new Exception("Niepoprawe obecne hasło.");
        }

        Optional<Account> accountFoundedByLogin = findAccountByLogin(userDetailDTO.getAccount());
        if (accountFoundedByLogin.isPresent() && accountFoundedByLogin.get().getAccountId() != userDetailDTO.getAccount().getAccountId()) {
            throw new Exception("Konto o podanym loginie już istnieje.");
        }

        Optional<UserDetail> accountFoundedByEmail = findUserDetailByEmail(userDetailDTO);
        if (accountFoundedByEmail.isPresent() && accountFoundedByEmail.get().getUserDetailId() != userDetailDTO.getUserDetailId()) {
            throw new Exception("Istnieje już konto przypisane do podanego adresu email.");
        }
    }

    public Account findAccountById(Long accountId) throws Exception {
        Optional<Account> account = accountDao.findById(accountId);
        if (account.get() == null) {
            throw new Exception(Account.class.getSimpleName());
        }

        return account.get();
    }

    private Optional<Account> findAccountByLogin(AccountDTO accountDTO) throws Exception {
        return accountDao.findAccountByLogin(accountDTO.getLogin());
    }

    private Optional<UserDetail> findUserDetailByEmail(UserDetailDTO userDetailDTO) throws Exception {
        return userDetailDao.findUserDetailsByEmail(userDetailDTO.getEmail());
    }
}
