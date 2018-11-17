package de.timmyrs;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ShutdownInMinutes extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		getConfig().addDefault("shutdownInMinutes", 1440L);
		getConfig().addDefault("disconnectMessage", "The server is going down for a quick self-maintenance restart. Reconnect in ~10 seconds.");
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		getServer().getScheduler().scheduleSyncDelayedTask(this, ()->
		{
			for(Player p : getServer().getOnlinePlayers())
			{
				p.kickPlayer(getConfig().getString("disconnectMessage"));
			}
			getServer().shutdown();
		}, getConfig().getLong("shutdownInMinutes") * 1200L);
	}
}
