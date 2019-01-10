package cn.inkroom.web.money.gate.handler.spring;

import cn.inkroom.web.money.gate.dto.ctv.EditorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler implements HttpMessageConverter<EditorDto>,HandlerMethodReturnValueHandler {
    private Logger log = LoggerFactory.getLogger(getClass());

    public boolean supportsReturnType(MethodParameter methodParameter) {
        log.info("类型支持=supportsReturnType");
        return true;
    }

    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        log.info("类型支持=handleReturnValue");
    }


    @Override
    public boolean canRead(Class<?> aClass, MediaType mediaType) {
        log.info("类型支持=canRead");
        return true;
    }

    @Override
    public boolean canWrite(Class<?> aClass, MediaType mediaType) {
        log.info("类型支持=canWrite");
        return true;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        log.info("类型支持=canRead");
        return new ArrayList<>();
    }

    @Override
    public EditorDto read(Class<? extends EditorDto> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        log.info("类型支持=read");
        return new EditorDto();
    }

    @Override
    public void write(EditorDto editorDto, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        log.info("类型支持=write");
    }

//    @Override
//    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
//        log.info("类型支持=write");
//    }
}
