package ca.qc.cegeptr.mat1892498.battleshipserver.requests.encoder;

import ca.qc.cegeptr.mat1892498.battleshipserver.Server;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;

public class ResponseEncoder extends MessageToByteEncoder<Response> {

    private final Charset charset = Charset.forName("UTF-8");

    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, ByteBuf byteBuf) throws Exception {
        String json = Server.gson.toJson(response);
        byteBuf.writeInt(json.length());
        byteBuf.writeCharSequence(json, charset);
    }

}
