package ca.qc.cegeptr.mat1892498.battleshipserver.requests.decoder;

import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class ResponseDecoder extends ReplayingDecoder<Response> {

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Gson gson = new Gson();
        int len = byteBuf.readInt();
        String json = byteBuf.readCharSequence(len, CharsetUtil.UTF_8).toString();
        Response requestData = gson.fromJson(json, Response.class);

        list.add(requestData);
    }
}
