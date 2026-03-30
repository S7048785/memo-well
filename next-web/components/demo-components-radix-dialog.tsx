import * as React from 'react';

import { Button } from '@/components/ui/button';
import {
  Dialog,
  DialogTrigger,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogDescription,
  DialogClose,
  DialogFooter,
  type DialogContentProps,
} from '@/components/animate-ui/components/radix/dialog';

export const RadixDialogDemo = () => {
  return (
    <Dialog>
      <DialogTrigger>Open Dialog</DialogTrigger>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Dialog Title</DialogTitle>
          <DialogDescription>Dialog Description</DialogDescription>
        </DialogHeader>
        <p>Dialog Content</p>
        <DialogFooter>
          <button>Accept</button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
};