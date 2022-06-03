package com.pcwk.ehr;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("tv")
@Component // lgTV @Component만 줘도 클래스 첫글자를 소문자로 바꿔서 자동으로 변환 생성
public class LgTV implements Tv {
	final Logger LOG = LogManager.getLogger(getClass());
	final String brand = "엘지Tv";

	@Resource(name = "sony99")
//	@Resource(name = "sonySpeaker")
//	@Resource(name = "appleSpeaker")
	private Speaker speaker;

	public LgTV() {
	}

	@Override
	public void powerOn() {
		LOG.debug(brand + " 전원을 켠다.");
	}

	@Override
	public void powerOff() {
		LOG.debug(brand + " 전원을 끈다.");
	}

	@Override
	public void volumeUp() {
		// LOG.debug(brand+" 볼륨을 높인다.");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// LOG.debug(brand+" 볼륨을 내린다.");
		speaker.volumeDown();
	}

}
