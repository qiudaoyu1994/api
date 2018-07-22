package com.mtdhb.api.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mtdhb.api.constant.ThirdPartyApplication;
import com.mtdhb.api.entity.Cookie;
import com.mtdhb.api.entity.view.CookieRankView;

/**
 * @author i@huangdenghe.com
 * @date 2018/03/03
 */
public interface CookieRepository extends CrudRepository<Cookie, Long> {

    long countByApplicationAndUserId(ThirdPartyApplication application, long userId);

    Cookie findByOpenId(String openId);

    List<Cookie> findByUserId(long userId);

    Cookie findByIdAndUserId(long id, long userId);

    List<Cookie> findByOpenIdIsNull();

    List<Cookie> findByIdIn(Set<Long> ids);

    @Query(value = "select c.userId as userId, count(*) as count from Cookie c where c.application=?1 group by c.userId order by count(*) desc, max(c.gmtCreate) asc")
    Page<CookieRankView> findCookieRankViewByApplication(ThirdPartyApplication application, Pageable pageable);

    Slice<Cookie> findByApplicationAndIdGreaterThan(ThirdPartyApplication application, long lower, Pageable pageable);

}
