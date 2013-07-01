package com.panguso.liufy.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Server {
    public static void main(String[] args) throws Exception {
        SocketAcceptor acceptor = new NioSocketAcceptor();
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        // 日志过滤器
        chain.addLast("logger", new LoggingFilter());
        chain.addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));

        acceptor.setHandler(new IoHandler() {

            @Override
            public void sessionOpened(IoSession session) throws Exception {
            }

            @Override
            public void sessionIdle(IoSession session,
                                    IdleStatus status) throws Exception {
            }

            @Override
            public void sessionCreated(IoSession session) throws Exception {
            }

            @Override
            public void sessionClosed(IoSession session) throws Exception {
            }

            @Override
            public void messageSent(IoSession session,
                                    Object message) throws Exception {
            }

            @Override
            public void messageReceived(IoSession session,
                                        Object message) throws Exception {
                System.out.println(message.toString());
                User user = new User();
                user.setName("server");
                user.setId(0L);
                session.write(user);

            }

            @Override
            public void exceptionCaught(IoSession session,
                                        Throwable cause) throws Exception {
            }
        });

        acceptor.bind(new InetSocketAddress("127.0.0.1", 8888));
    }

}
