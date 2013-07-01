package com.panguso.liufy.mina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Client {

	public static void main(String[] args) {
		NioSocketConnector connector = new NioSocketConnector();
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec",
		        new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		connector.setHandler(new IoHandler() {

			@Override
			public void sessionOpened(IoSession session) throws Exception {
			}

			@Override
			public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
			}

			@Override
			public void sessionCreated(IoSession session) throws Exception {
			}

			@Override
			public void sessionClosed(IoSession session) throws Exception {
			}

			@Override
			public void messageSent(IoSession session, Object message) throws Exception {
			}

			@Override
			public void messageReceived(IoSession session, Object message) throws Exception {
			}

			@Override
			public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
			}
		});

		ConnectFuture connectFuture = connector.connect(new InetSocketAddress("127.0.0.1", 8888));
		connectFuture.awaitUninterruptibly();
		IoSession session = connectFuture.getSession();

		User user = new User();
		user.setName("朴海林");
		user.setId(13811797983L);
		session.write(user);
	}
}
