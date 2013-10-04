package ezvcard.types;

import static ezvcard.util.TestUtils.assertWarnings;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import ezvcard.VCard;
import ezvcard.VCardVersion;

/*
 Copyright (c) 2013, Michael Angstadt
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met: 

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer. 
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution. 

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those
 of the authors and should not be interpreted as representing official policies, 
 either expressed or implied, of the FreeBSD Project.
 */

/**
 * @author Michael Angstadt
 */
public class ListPropertyTest {
	@Test
	public void validate() {
		VCard vcard = new VCard();

		ListPropertyImpl zeroItems = new ListPropertyImpl();
		assertWarnings(1, zeroItems.validate(VCardVersion.V2_1, vcard));
		assertWarnings(1, zeroItems.validate(VCardVersion.V3_0, vcard));
		assertWarnings(1, zeroItems.validate(VCardVersion.V4_0, vcard));

		ListPropertyImpl oneItem = new ListPropertyImpl();
		oneItem.addValue("one");
		assertWarnings(0, oneItem.validate(VCardVersion.V2_1, vcard));
		assertWarnings(0, oneItem.validate(VCardVersion.V3_0, vcard));
		assertWarnings(0, oneItem.validate(VCardVersion.V4_0, vcard));
	}

	@Test
	public void removeValue() {
		ListPropertyImpl property = new ListPropertyImpl();
		property.addValue("one");
		property.addValue("two");
		property.addValue("three");
		property.removeValue("two");
		property.removeValue("four");
		assertEquals(Arrays.asList("one", "three"), property.getValues());
	}

	private class ListPropertyImpl extends ListProperty<String> {
		//empty
	}
}