package com.ronustine.splendidpro.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * StreamController：下载测试
 *
 * @author ronustine
 */
@RequestMapping("test")
@Controller
public class StreamController {

    Logger log = LoggerFactory.getLogger(StreamController.class);

    @ApiOperation("export-test")
    @GetMapping("/export-test")
    public ResponseEntity<StreamingResponseBody> exportTradeOrders() {
        final StreamingResponseBody body = new StreamingResponseBody() {
            @Override
            public void writeTo(final OutputStream outputStream) throws IOException {
                final String title = "id,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称," +
                        "名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称,名称";
                outputStream.write(title.getBytes());
                final AtomicInteger i = new AtomicInteger();
                while (true) {
                    final int id = i.getAndIncrement();
                    final String alphabetic = RandomStringUtils.randomAlphabetic(10);
                    String temp = MessageFormat.format(
                            "{0},{1},{2},{3},{4},{5},{6},{7},{8},{9},{10},{11},{12},{13}," +
                                    "{14},{15},{16},{17},{18},{19},{20},{21},{22},{23},{24},{25},{26},{27}," +
                                    "{28},{29},{30},{31},{32},{33},{34},{35},{36}\n",
                            id, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic,
                             alphabetic, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic,
                            alphabetic, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic,
                            alphabetic, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic,
                            alphabetic, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic,
                            alphabetic, alphabetic, alphabetic, alphabetic, alphabetic, alphabetic);
                    log.info(temp);
                    outputStream.write(temp.getBytes());
                    outputStream.flush();

                    if (id >= 50000) {
                        return;
                    }
                }
            }
        };
        return ResponseEntity
                .ok()
                //.contentType(MediaType.valueOf("application/csv"))
                .contentType(MediaType.TEXT_PLAIN)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.csv")
                .body(body);
    }

}
