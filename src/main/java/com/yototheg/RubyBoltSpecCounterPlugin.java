package com.yototheg;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ItemID;
import net.runelite.api.events.SoundEffectPlayed;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.InfoBoxMenuClicked;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

import javax.inject.Inject;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
	name = "Ruby Bolt Spec Counter",
	description = "Counts how many times your Ruby bolt has proc'd"
)
public class RubyBoltSpecCounterPlugin extends Plugin
{
	private static final int RUBY_BOLT_SPEC_SOUND_EFFECT = 2911;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private ItemManager itemManager;

	private RubyBoltSpecCounter counterInfoBox = null;

	@Override
	protected void startUp() throws Exception
	{
		final BufferedImage img = itemManager.getImage(ItemID.RUBY_DRAGON_BOLTS_E, 0, false);
		counterInfoBox = new RubyBoltSpecCounter(img, this, 0, "Ruby Bolt Counter");
		infoBoxManager.addInfoBox(counterInfoBox);
	}

	@Override
	protected void shutDown() throws Exception
	{
		infoBoxManager.removeInfoBox(counterInfoBox);
	}

	@Subscribe
	public void onSoundEffectPlayed(SoundEffectPlayed event)
	{
		if (event.getSoundId() == RUBY_BOLT_SPEC_SOUND_EFFECT)
		{
			incrementCounterInfoBox();
		}
	}

	@Subscribe
	public void onInfoBoxMenuClicked(InfoBoxMenuClicked e)
	{
		if (!e.getInfoBox().equals(counterInfoBox))
		{
			return;
		}

		if (e.getEntry().getOption().equals("Reset"))
		{
			counterInfoBox.setCount(0);
		}
	}

	public void incrementCounterInfoBox()
	{
		counterInfoBox.setCount(counterInfoBox.getCount() + 1);
	}
}
