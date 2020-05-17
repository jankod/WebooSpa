package hr.ja.weboo.lib;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Setter
public class RequestResponse {

    private HttpServletRequest req;

    private HttpServletResponse res;
}
