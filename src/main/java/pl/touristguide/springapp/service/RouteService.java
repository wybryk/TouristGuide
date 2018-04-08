package pl.touristguide.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.touristguide.model.Account;
import pl.touristguide.model.Route;
import pl.touristguide.model.UserDetail;
import pl.touristguide.springapp.dao.RouteDao;
import pl.touristguide.springapp.dto.RouteDTO;
import pl.touristguide.springapp.mapper.RouteMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteDao routeDao;
    private final UserService userService ;

    @Autowired
    public RouteService(RouteDao routeDao, UserService userService) {
        this.routeDao = routeDao;
        this.userService = userService;
    }

    public List<RouteDTO> getRoutes(Long accountId) throws Exception {
        Account account = this.userService.findAccountById(accountId);
        return account.getRoutes()
                .stream()
                .map(tmpRoute -> RouteMapper.toRouteDTO(tmpRoute))
                .collect(Collectors.toList());
    }

    public RouteDTO getRoute(Long routeId) throws Exception {
        return RouteMapper.toRouteDTO(this.routeDao.findById(routeId).get());
    }

    public void insertRoute(RouteDTO routeDTO) throws Exception {
        Account account = userService.findAccountById(routeDTO.getAccount().getAccountId());
        this.routeDao.save(RouteMapper.toRoute(routeDTO, account));
    }

    public void updateRoute(Long routeId, RouteDTO routeDTO) throws Exception {
        Account account = userService.findAccountById(routeDTO.getAccount().getAccountId());
        this.routeDao.save(RouteMapper.toRoute(routeDTO, account));
    }

    public void deleteRoute(Long routeId) throws Exception {
        this.routeDao.deleteById(routeId);
    }
}
