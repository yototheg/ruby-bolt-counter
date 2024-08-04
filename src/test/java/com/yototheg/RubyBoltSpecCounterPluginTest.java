package com.yototheg;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RubyBoltSpecCounterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RubyBoltSpecCounterPlugin.class);
		RuneLite.main(args);
	}
}