package ca.qc.cegeptr.mat1892498.battleshipserver.requests.decoder;

import ca.qc.cegeptr.mat1892498.battleshipserver.Server;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class ResponseDecoder extends ReplayingDecoder<Response> {

    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readInt();
        String json = byteBuf.readCharSequence(len, CharsetUtil.UTF_8).toString();
        Response requestData = Server.gson.fromJson(json, Response.class);

        list.add(requestData);
    }
}
