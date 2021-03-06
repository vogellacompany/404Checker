/**
 * Copyright 2013 me, under Beerware license.
 */
package com.example.checker.extract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import com.example.checker.Link;
import com.example.checker.URLLink;

/**
 * @author markus
 * 
 */
public class HTMLRegexExtractor extends RegexExtractor {

	private static final Logger LOGGER = Logger
			.getLogger(HTMLRegexExtractor.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.checker.extract.IExtractor#extractLinks(java.net.URL,
	 * java.util.Set)
	 */
	@Override
	public void extractLinks(Link link, Set<Link> links) throws IOException {
		LOGGER.log(Level.FINE,
				String.format("Extracting links on page %s", link.toString()));
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				link.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
			final Matcher match = this.matcher.matcher(line);
			if (match.matches()) {
				// second group is the match, first is the full line
				links.add(new URLLink(new URL(match.group(1))));
			}
		}
		reader.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.checker.extract.IExtractor#getContentType()
	 */
	@Override
	public String getContentType() {
		return "text/html; charset=UTF-8";
	}
}
